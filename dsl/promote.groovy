// Variables available for use in DSL code
def pluginName = args.pluginName
def pluginKey = getProject("/plugins/$pluginName/project").pluginKey
def pluginDir = getProperty("/server/settings/pluginsDirectory").value + "/" + pluginName
// END Variables

// Sample plugin project content.  pluginName can be replaced by a name
// to create a non-plugin project
project pluginName,{
} // project pluginName

