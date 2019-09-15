@echo off
call "C:\Program Files\Microsoft SDKs\Windows\v7.1\Bin\SetEnv.cmd" /x64 /Debug
call gradlew assemble
%GRAAL_HOME%\bin\native-image --class-path ./build/libs/graal-*-all.jar  -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" -H:Name=graal -H:Class=graal.Application -H:+ReportUnsupportedElementsAtRuntime -H:+AllowVMInspection -H:+ReportExceptionStackTraces --initialize-at-run-time=io.netty.handler.ssl.JdkSslContext

REM -H:EnableURLProtocols=http
REM -H:+JNI -H:ReflectionConfigurationFiles="./netty_reflection_config.json" --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder
REM --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.codec.http.HttpObjectDecoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom  --rerun-class-initialization-at-runtime=sun.security.jca.JCAUtil$CachedSecureRandomHolder