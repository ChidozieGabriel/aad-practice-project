pipeline {
  agent any

  options {
    parallelsAlwaysFailFast()
  }

  stages {
    stage('Make gradle executable') {
      steps {
        script {
          if (isUnix()) {
            shell('chmod -x gradlew')
          }
        }
      }
    }

    stage('Parallel') {
      parallel {
        stage('Integration Tests') {
          steps {
            shell('bundle exec fastlane integration')
          }
          post {
            always {
              junit '**/build/outputs/androidTest-results/connected/flavors/*AndroidTest/TEST-*.xml'
            }
          }
        }
        stage('Sequential') {
          stages {
            stage('Clean') {
              steps {
                shell('./gradlew clean')
              }
            }

            stage('Build') {
              steps {
                shell('./gradlew assembleDebug')
              }
            }

            stage('Unit Tests') {
              steps {
                shell('./gradlew testDebugUnitTest')
              }
              post {
                always {
                  junit '**/build/test-results/test*/TEST-*.xml'
                }
              }
            }
          }   
        }
      }
    }
  }
}

// fix windows shell problem
def shell(String command) {
  if (isUnix()) {
    sh command
  } else {
    bat command
  }
}
