job('NodeJS Docker example') {
    scm {
        git('https://github.com/Liranmoyal/node-hello.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Liranmoyal')
            node / gitConfigEmail('liran308@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node 17.0.1') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('liran308/test')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

