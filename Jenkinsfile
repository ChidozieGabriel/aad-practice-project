pipeline {
  agent any

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

    stage('Integration Tests') {
      steps {
        shell('bundle exec fastlane integration')
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
