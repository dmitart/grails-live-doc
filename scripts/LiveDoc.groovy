import org.codehaus.groovy.tools.groovydoc.*

target(main: "generates documentation from specs") {
  GroovyDocTool doc = new GroovyDocTool(new FileSystemResourceManager(),
      ["test/functional/"].toArray(new String[0]),
      [ // top level templates
          liveDocPluginDir.toString() + "/grails-app/templates/top-level/index.html",
          liveDocPluginDir.toString() + "/grails-app/templates/top-level/overview-frame.html", // needs all package names
          liveDocPluginDir.toString() + "/grails-app/templates/top-level/allclasses-frame.html", // needs all packages / class names
          liveDocPluginDir.toString() + "/grails-app/templates/top-level/overview-summary.html", // needs all packages
          liveDocPluginDir.toString() + "/grails-app/templates/top-level/stylesheet.css",
      ].toArray(new String[0]),
      [ // package level templates
          liveDocPluginDir.toString() + "/grails-app/templates/package-level/package-frame.html",
          liveDocPluginDir.toString() + "/grails-app/templates/package-level/package-summary.html"
      ].toArray(new String[0]),
      [ // class level templates
          liveDocPluginDir.toString() + "/grails-app/templates/class-level/classDocName.html"
      ].toArray(new String[0]),
      [new LinkArgument()], new Properties()
  )
  File folder = new File("test/functional/")
  folder.eachFileRecurse {
    if (it.isFile()) {
      if (((it.name =~ /.*Spec.groovy/) && !(it.absolutePath.indexOf('.svn') > -1)) ||
          (it.name == 'package.html') ) {
        doc.add([it.absolutePath.substring(folder.absolutePath.length()+1)])
      }
    }
  }
  doc.renderToOutput(new FileOutputTool(), "target/specs/")
}

setDefaultTarget(main)
