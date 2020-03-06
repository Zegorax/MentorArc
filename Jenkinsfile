pipeline {
    agent {
        docker {
            image 'docker:dind'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    stages {
        stage('Build') { 
            steps {
                script {
                    docker.image('maven:3-alpine').inside { c ->
                        checkout scm
                        sh 'mv src/main/resources/application.properties.production src/main/resources/application.properties'
                        sh 'mvn -B -DskipTests clean package' 
                    }
                }

                
            }
        }
        stage('Test') {
            steps{
                script {
                    docker.image('mysql').withRun('-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mentorarc -e MYSQL_USER=mentorarc -e MYSQL_PASSWORD=mentorarc') { c ->
                        docker.image('mysql').inside("--link ${c.id}:db") {
                            /* Wait until mysql service is up */
                            sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
                        }

                        docker.image('maven:3-alpine').inside("--link ${c.id}:db") {
                            /* Wait until mysql service is up */
                            sh 'mv src/main/resources/application.properties.production src/main/resources/application.properties'
                            sh 'mvn test'
                        }
                        
                    }
                }
            }
            
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}