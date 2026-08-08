pipeline {
    agent any

    tools {
        // Jenkins मधील Maven चे नाव
        maven 'Maven3' 
    }

    stages {
        // Topic 1: Source Code Management (SCM) & Checkout
        stage('Assignment Topic 1: Checkout SCM') {
            steps {
                echo 'Fetching Source Code from GitHub Repository...'
                git branch: 'main', url: 'https://github.com/Ritesh206-salvi/sample-java-webapp.git'
            }
        }

        // Topic 2: Maven Build Stage (Compiling & Packaging)
        stage('Assignment Topic 2: Build & Package') {
            steps {
                echo 'Compiling Java Code and Packaging into WAR File...'
                // Windows साठी bat वापरले आहे
                bat 'mvn clean package'
            }
        }

        // Topic 3: Testing Stage (Unit Tests Execution)
        stage('Assignment Topic 3: Execute Unit Tests') {
            steps {
                echo 'Running Automated Unit Tests with Maven...'
                bat 'mvn test'
            }
        }

        // Topic 4: Deployment Stage (Deploying WAR to Tomcat Server)
        stage('Assignment Topic 4: Deploy to Tomcat Server') {
            steps {
                echo 'Deploying WAR file to Apache Tomcat on Port 8081...'
                deploy adapters: [tomcat9(credentialsId: 'tomcat-manager-creds', path: '', url: 'http://localhost:8081/')], 
                       contextPath: 'sample-java-webapp', 
                       war: 'target/*.war'
            }
        }
    }

    // Pipeline Post-Actions
    post {
        always {
            echo 'Assignment Pipeline execution completed.'
        }
        success {
            echo 'SUCCESS: Application successfully deployed to Tomcat!'
            echo 'Access your App here: http://localhost:8081/sample-java-webapp'
        }
        failure {
            echo 'FAILURE: Pipeline execution failed. Please check the logs above.'
        }
    }
}