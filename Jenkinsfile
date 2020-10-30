pipeline {
    agent any

    stages {
    
        stage('Dependency-Check') {
            steps {
                sh "mvn dependency-check:check"
                //sh "docker run owasp/dependency-check -scan /src --out /report"
            }
        }
    
        stage('BUILD') {
            steps {
                sh "mvn clean package"
            }
        }
	
	stage("Run Gatling") {
            steps {
                sh 'mvn gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
        
        stage('Analise com SonarQube') {
            steps {
                withSonarQubeEnv('sonar'){
                    sh "mvn sonar:sonar -Dsonar.projectKey=backfalcon"
                }
            }
        }

        /*stage('Salvar Artefato') {
            steps {
                script{
                    def pom = readMavenPom file: 'pom.xml'
                    
                    nexusPublisher nexusInstanceId: 'nexus', \
                    nexusRepositoryId: 'maven-releases', \
                    packages: [[$class: 'MavenPackage', \
                        mavenAssetList: [[classifier: '', extension: '', filePath: env.WORKSPACE+'/target/backend.jar']], mavenCoordinate: [artifactId: "${pom.artifactId}", groupId: "${pom.groupId}", packaging: "${pom.packaging}", version: "${pom.version}"]]]
                }
            }
        }*/
        
    }
    post {
        always {
            dependencyCheckPublisher pattern: 'build/reports/dependency-check-report.xml'
        }
    }
}
