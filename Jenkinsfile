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
						sh 'rm src/main/resources/application.properties'
                        sh 'mv src/main/resources/application.properties.production src/main/resources/application.properties'
                        sh 'mvn -B -DskipTests clean package'
						stash name: 'mentorarc', includes: '**'
                    }
                }
            }
        }
        // stage('Test') {
        //     steps{
        //         script {
        //             docker.image('mysql').withRun('-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mentorarc -e MYSQL_USER=mentorarc -e MYSQL_PASSWORD=mentorarc') { c ->
        //                 docker.image('mysql').inside("--link ${c.id}:db") {
        //                     /* Wait until mysql service is up */
        //                     sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
        //                 }

        //                 docker.image('maven:3-alpine').inside("--link ${c.id}:db") {
        //                     unstash 'mentorarc'
        //                     sh 'mvn test'
        //                 }
        //             }
        //         }
        //     }
            
        //     post {
        //         always {
        //             junit 'target/surefire-reports/*.xml'
        //         }
        //     }
        // }
        // stage('Quality'){
        //     steps{
        //         script{
        //             withCredentials([usernamePassword(credentialsId: 'SonarCloud_Zegorax_Token', passwordVariable: 'SONARCLOUD_API_TOKEN', usernameVariable: 'SONARCLOUD_API_USER')]) {
        //                 docker.image('mysql').withRun('-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mentorarc -e MYSQL_USER=mentorarc -e MYSQL_PASSWORD=mentorarc') { c ->
        //                     docker.image('mysql').inside("--link ${c.id}:db") {
        //                         /* Wait until mysql service is up */
        //                         sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
        //                     }
        //                     docker.image('maven:3-alpine').inside("--link ${c.id}:db") {
        //                             unstash 'mentorarc'
        //                             sh 'mvn verify sonar:sonar'
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }
		stage('IntegrationTests') {
            steps{
                script {
                    docker.image('mysql').withRun('-e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mentorarc -e MYSQL_USER=mentorarc -e MYSQL_PASSWORD=mentorarc') { c ->
                        docker.image('mysql').inside("--link ${c.id}:db") {
                            /* Wait until mysql service is up */
                            sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'
                        }

						docker.image('maven:3-alpine').withRun("--link ${c.id}:db") { d ->
							docker.image('maven:3-alpine').inside("--link ${c.id}:db") { e ->
								sh 'sleep 10'
								unstash 'mentorarc'
								sh 'ls -al target'
								sh 'nohup java -jar target/MentorArc-0.0.1-SNAPSHOT.jar &'

								docker.image('lucienmoor/katalon-for-jenkins:latest').inside("--link ${e.id}:mentorarc") { 
									sh 'sleep 20'
									sh 'curl mentorarc:8081'
								}
							}
						}
                    }
                }
            }
        }
        // stage ('Performance') {
        // octoPerfTest credentialsId: 'XXXXXX-XXX-XXX-XXXX', scenarioId: 'XXXXXXX'
        // }

        // stage ('JunitReport') {
        //     junit 'junit-report.xml'
        // }

        // stage ('PerfReport') {
        //     perfReport modeThroughput: true, sourceDataFiles: '**/*.jtl'
        // }
    }
}