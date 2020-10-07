pipeline {
  agent any

    environment {
      // Fastlane Environment Variables
      // HHOME = "C:/Users/ChidozieNnabugwu"
      // PATH = "$HHOME/.fastlane/bin:" +
      //         "$HHOME/.rvm/gems/ruby-2.5.3/bin:" +
      //         "$HHOME/.rvm/gems/ruby-2.5.3@global/bin:" +
      //         "$HHOME/.rvm/rubies/ruby-2.5.3/bin:" +
      //         "/usr/local/bin:" +
      //         "/c/Ruby26-x64/bin/fastlane" + 
      //         "$PATH"
      PATH = "C:/Ruby26-x64/bin;C:/Program Files/Git/usr/bin;C:/Windows/System32/bash.exe;C:/Windows/System32/wsl.exe;$PATH"
      LC_ALL = "en_US.UTF-8"
      LANG = "en_US.UTF-8"

      VERSION_NAME = ""
      VERSION_SUFFIX = ""
      APP_VERSION_NAME = ""
      VERSION_CODE = ""
      DROPBOX_FOLDER = ""
      PROGUARD_ENABLED = ""
      JIRA_PROJECT_KEY = ""
  }

  stages {
    // stage('Clean') {
    //   steps {
    //     shell('bundle exec fastlane clean')
    //   }
    // }

    // stage('Build') {
    //   steps {
    //     shell('bundle exec fastlane build')
    //   }
    // }

    // stage('Unit Tests') {
    //   steps {
    //       shell('bundle exec fastlane test')
    //   }
    //   post {
    //       always {
    //           junit '**/build/test-results/test*/TEST-*.xml'
    //       }
    //   }
    // }

    stage('Integration Tests') {
      steps {
        echo HOME
        shell('bash which sudo')
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
    // bat command
    bat command
  }
}
