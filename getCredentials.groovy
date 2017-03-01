import groovyx.net.http.RESTClient

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.2')

public class getCredentials {
    def doHttpGet(String url) {
	def commanderServer = 'https://' + System.getenv('COMMANDER_SERVER')
	 
        def sessionId = System.getenv('COMMANDER_SESSIONID')
	def client = new RESTClient(commanderServer)
	client.ignoreSSLIssues()
	// Register error handler
        client.handler.failure = { resp, reader ->
          println "Error details: $reader"
          System.exit(-1)
        }

	def resp = client.get(path: url, headers:['Cookie':"sessionId="+sessionId,  'Accept': 'application/json'])
	assert resp.status == 200 
        // print resp.getData()
 	return resp
    }

    def getCreds(String credName) {
        def jobStepId = '$[/myJobStep/jobStepId]'
	def result = doHttpGet("/rest/v1.0/jobSteps/$jobStepId/credentialPaths/$credName")
	return result
    }
}

def gc=new getCredentials()
def resp=gc.getCreds('creds') // %2Fprojects%2Fsandbox%2F

def userName = resp.getData().credential.userName
def password = resp.getData().credential.password

println("User: $userName")
println("Password: $password")

