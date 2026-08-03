pipeline {
    agent any

    tools {
        // These names must match what you configured in
        // Manage Jenkins -> Tools
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {
        TOMCAT_URL   = 'http://localhost:8081'
        TOMCAT_CREDS = credentials('tomcat-manager-creds') // set up in Jenkins Credentials
        WAR_NAME     = 'sample-java-webapp'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                bat """
                    curl -u %TOMCAT_CREDS_USR%:%TOMCAT_CREDS_PSW% ^
                      -T target\\%WAR_NAME%.war ^
                      "%TOMCAT_URL%/manager/text/deploy?path=/%WAR_NAME%&update=true"
                """
            }
        }

        stage('Smoke Test') {
            steps {
                bat """
                    timeout /t 10
                    curl -f %TOMCAT_URL%/%WAR_NAME%/ || exit 1
                """
            }
        }
    }

    post {
        success {
            echo "Build #${env.BUILD_NUMBER} deployed successfully to Tomcat."
        }
        failure {
            echo "Build #${env.BUILD_NUMBER} failed."
        }
    }
}
