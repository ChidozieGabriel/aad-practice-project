pipeline {
  agent any

  stages {
    stage('Make gradle executable') {
      steps {
        if (isUnix()) {
          shell('chmod -x gradlew')
        }
      }
    }

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
        // shell('sdkmanager "system-images;android-23;default;x86"')
        shell('bundle exec fastlane integration')
      }
      post {
        always {
          junit '**/build/outputs/androidTest-results/connected/flavors/*AndroidTest/TEST-*.xml'
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
