// Get a specific project description
// with REST API using ec-groovy

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')

import groovyx.net.http.RESTClient
import org.apache.http.HttpRequest

def _server="SERVER
def _user="USER"
def _pwd="PASSWORD"

def flow = new RESTClient( 'https://' + _server + ':8443/rest/v1.0/' )
flow.ignoreSSLIssues()

// Authentication
def auth = _user + ":" + _pwd
flow.headers['Authorization'] = 'Basic ' + auth.getBytes().encodeBase64()

// REST call to get project information
def resp = flow.get( path : 'projects/Training_user' ) 

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
println json
println "\n\n"
println "Description: " + json.project.description
