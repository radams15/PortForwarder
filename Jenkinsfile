pipeline {
    agent{
        docker{
            image 'maven:3-eclipse-temurin-11'
        }
    }

    stages{
        stage('Build'){
            steps{
                sh 'mvn compile package'

                archiveArtifacts artifacts: 'target/PortForwarder-*.jar'
            }
        }
    }
}
