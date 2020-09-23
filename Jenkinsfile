pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        gradlew('clean')
      }
    }

    stage('Build') {
      steps {
        gradlew('assembleDebug')
      }
    }

    stage('Unit Tests') {
      steps {
          gradlew('testDebugUnitTest')
      }
      post {
          always {
              junit '**/build/test-results/test*/TEST-*.xml'
          }
      }
    }

    
  }
}

def gradlew(String... args) {
  sh ./gradlew clean -s

}


def shell(String command) {
   if (isUnix()) {
    sh command
  } else {
    bat command
  }
}
