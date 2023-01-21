Pod::Spec.new do |spec|
    spec.name                     = 'realtime'
    spec.version                  = '1.0'
    spec.homepage                 = 'https://github.com/Cherrio-LLC/RealTime'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Library that enables smooth consumption of Dilivva websocket server'
    spec.vendored_frameworks      = 'build/cocoapods/framework/RealTime.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '13'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':realtime',
        'PRODUCT_MODULE_NAME' => 'RealTime',
    }
                
    spec.script_phases = [
        {
            :name => 'Build realtime',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end