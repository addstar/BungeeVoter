package au.com.addstar.bungeevoter;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeVoter extends Plugin implements Listener {
	private BungeeVoter plugin = this;
	
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, this);
		getProxy().registerChannel("BungeeVoter");
	}
	
	@EventHandler
    public void onVote(VotifierEvent event) {
		Vote v = event.getVote();

		plugin.getLogger().info("Vote received: " 
				+ v.getUsername() + ", " 
				+ v.getAddress() + ", " 
				+ v.getTimeStamp() + ", " 
				+ v.getServiceName());
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try {
			out.writeUTF("vote");
			out.writeUTF(v.getUsername());
			out.writeUTF(v.getAddress());
			out.writeUTF(v.getTimeStamp());
			out.writeUTF(v.getServiceName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		for(ServerInfo server : getProxy().getServers().values()) {
			if(!server.getPlayers().isEmpty())
				server.sendData("BungeeVoter", b.toByteArray());
		}
	}
}
