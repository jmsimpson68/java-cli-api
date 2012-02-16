package ballew.samples.cli.client.commands;

import ballew.samples.cli.client.SampleCLIContext;
import ballew.tools.cli.api.Command;
import ballew.tools.cli.api.CommandResult;
import ballew.tools.cli.api.annotations.CLICommand;
import ballew.tools.cli.api.console.Console;

import com.beust.jcommander.Parameter;

/**
 * Login a user.
 * @author Sean
 *
 */
@CLICommand(name="login", description="Logs a user into the client.")
public class LoginCommand extends Command<SampleCLIContext> {
	
	@Parameter(names={"-u", "--user"}, description="The username to login with.", required=true)
	private String _user;
	
	@Override
	public CommandResult innerExecute(SampleCLIContext context) {
		context.setLoggedInUser(_user);
		Console.info("Logged in as user ["+_user+"].");
		return CommandResult.OK;
	}

}