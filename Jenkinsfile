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
          // gradlew('testDebugUnitTest')
          shell('fastlane test')
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
  def command = "./gradlew ${args.join(' ')} -s"
  // sh command
  shell(command)
}

// fix windows shell problem
def shell(String command) {
   if (isUnix()) {
    sh command
  } else {
    bat command
  }
}
