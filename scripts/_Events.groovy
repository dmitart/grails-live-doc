eventPackagePluginStart = {
  compile()
  ant.jar ( destfile : 'lib/livedoc.jar' , basedir : 'target/classes', includes:"com/evolution/**")
}