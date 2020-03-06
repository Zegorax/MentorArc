pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        agent{
                docker.image('mysql').withRun('-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mentorarc -e MYSQL_USER=mentorarc -e MYSQL_PASSWORD=mentorarc') { c ->
                    docker.image('maven:3-alpine').inside("--link ${c.id}:db") {
                        /* Wait until mysql service is up */
                        sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'

                        sh 'mvn test'
                    }
                    
                }
            }
        stage('Test') {
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}