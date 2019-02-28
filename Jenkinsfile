def containerId=""
pipeline {
    agent none
    stages {

        stage('Build') {
            agent {
                    docker {
                        image 'maven:3-alpine'
                        args '-v /root/.m2:/root/.m2'
                    }
                  }
            steps {
                    sh 'mvn -X clean install -DskipTests'
                  }
            }

        stage('Build DockerImage') {
            agent any
            steps{
                    script{
                        containerId = sh (
                        script :'docker ps -aqf "name=ecart"',
                        returnStdout: true
                        ).trim()
                            if("${containerId}"!= ''){
                                  sh 'docker stop ecart'
                                  sh 'docker rm ecart'
                                  sh 'docker rmi $(docker images --filter=reference=ecart --format "{{.ID}}")'
                            }
                    }
                    sh 'docker build -t ecart:1.0 .'
                }
         }

        stage('Deployment') {
            agent any
             steps {
                    sh 'sh dockercompose.sh'
                }
        }

        stage('Publish Image') {
           agent any
           steps {
                sh 'docker commit $(docker ps -aqf "name=ecart") thedarkcoderrises/ecart:1.0.${BUILD_NUMBER}'
               withDockerRegistry([ credentialsId: "thedarkcoderrises-dockerhub", url: "" ]) {
                 sh 'docker push thedarkcoderrises/ecart:1.0.${BUILD_NUMBER}'
               }
                sh 'docker rmi $(docker images --filter=reference=thedarkcoderrises/ecart:1.0.${BUILD_NUMBER} --format "{{.ID}}")'
            }
        }
    }
 }