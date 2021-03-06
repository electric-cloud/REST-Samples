// This is a basic example to get a list of projects
// using the REST API with ec-groovy

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')

import groovyx.net.http.RESTClient
import org.apache.http.HttpRequest

def _server="SERVER"    // Your server
def _user="USER"        // User to authenticate
def _pwd="PASSWORD"     // Password

def flow = new RESTClient( 'https://' + _server + ':8443/rest/v1.0/' )
flow.ignoreSSLIssues()

// Authentication
def auth= _user + ":" + _pwd
flow.headers['Authorization'] = 'Basic '+ auth.getBytes().encodeBase64()

// REST call to get project list
def resp = flow.get( path : 'projects' ) // Get list of projects

if (resp == null ) {
   println "Could not get project list on the Commander. Request failed"
   exit 1
}

if (resp.status != 200) {
   println "Commander did not respond with 200 for retrieving project list"
   exit 2
}

// The HTTP client returns JSON
def json = resp.getData()

for (proj in json.project) {
	if (proj.pluginName == null) {
		println proj.projectName 
    }
}
