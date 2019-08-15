pipeline {
    agent any

    triggers {
            pollSCM('*/3 * * * *')
    }

    environment {
        DOCKER_USERNAME = 'sivaprasadreddy'
        APPLICATION_NAME = 'todolist'
    }

    parameters {
        booleanParam(name: 'PUBLISH_TO_DOCKERHUB', defaultValue: true, description: 'Publish Docker Image to DockerHub?')
    }

    stages {
        stage('Test') {
            steps {
                sh './mvnw clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage("Publish to DockerHub") {
            when {
                expression { params.PUBLISH_TO_DOCKERHUB == true }
            }
            steps {
                  sh "docker build -t ${env.DOCKER_USERNAME}/${env.APPLICATION_NAME}:${BUILD_NUMBER} -t ${env.DOCKER_USERNAME}/${env.APPLICATION_NAME}:latest ."

                  withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                    credentialsId: 'docker-hub-credentials',
                                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                      sh "docker login --username $USERNAME --password $PASSWORD"
                  }
                  sh "docker push ${env.DOCKER_USERNAME}/${env.APPLICATION_NAME}:latest"
                  sh "docker push ${env.DOCKER_USERNAME}/${env.APPLICATION_NAME}:${BUILD_NUMBER}"

            }
        }
    }
}
