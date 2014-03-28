package au.com.addstar.bungeevoter;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeVoter extends Plugin implements Listener {
	private BungeeVoter plugin = this;
	
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, this);
	}
	
	@EventHandler
    public void onVote(VotifierEvent event) {
		Vote v = event.getVote();

		plugin.getLogger().info("Vote received: " 
				+ v.getUsername() + ", " 
				+ v.getAddress() + ", " 
				+ v.getTimeStamp() + ", " 
				+ v.getServiceName());
	}
}
