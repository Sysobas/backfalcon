pipeline {
    agent any
    stages {
        stage("Maven build") {
            steps {
                sh 'mvn clean'
            }
        }
        stage("Gatling run") {
            steps {
                sh 'mvn gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}
