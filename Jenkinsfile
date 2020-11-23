pipeline {
    agent any
    stages {
        stage("Maven build") {
            steps {
                sh 'mvn -B clean package'
            }
        }
        stage("Gatling run") {
                sh 'mvn gatling:test'

            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}
