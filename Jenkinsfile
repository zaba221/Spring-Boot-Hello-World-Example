pipeline {
    agent any
    
    tools {
        maven 'localMaven'
    }
    
    stages {
        
        stage('Checkout') {
            steps {
                echo "-=- Checkout project -=-"
                git url: 'https://github.com/zaba221/Spring-Boot-Hello-World-Example.git'
            }
        }
        
        stage('Package') {
            steps {
                echo "-=- packaging project -=-"
                sh 'clean package -Dmaven.test.skip=true'
            }
            
        }
        stage('Test') {
            steps {
                echo "-=- Test project -=-"
                sh 'mvn clean test'
            }
            
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Code coverage') {
            steps {
                jacoco( 
                      execPattern: 'target/*.exec',
                      classPattern: 'target/classes',
                      sourcePattern: 'src/main/java',
                      exclusionPattern: 'src/test*'
                )
            }
        }
        stage('Sanity check') {
          steps {
            echo "-=- Sanity Check Test project -=-"
            sh 'mvn --batch-mode checkstyle:checkstyle pmd:pmd'
          }
          post {
            always {
              recordIssues enabledForFailure: true, tools: [checkStyle()]
              recordIssues enabledForFailure: true, tool: pmdParser(pattern: '**/target/pmd.xml')
            }
          }
        }		
        stage('SonarQube Report') {
            steps {
              echo "-=- Running analyse with sonar -=-"
              withSonarQubeEnv('sonarqube') {
                sh 'mvn sonar:sonar'
              }
            }
        }
    }
}
