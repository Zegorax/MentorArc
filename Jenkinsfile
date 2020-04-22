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
						sh 'while ! mysqladmin ping -hdb --silent; do sleep 1; done'

						docker.image('maven:3-alpine').inside("--link ${c.id}:db") { d ->
							unstash 'mentorarc'
							sh 'java -jar ./MentorArc/target/MentorArc-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'

							docker.image('lucienmoor/katalon-for-jenkins:latest').inside("--link ${d.id}") { 
								sh 'sleep 20'
								sh 'curl mentorarc:8081'
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