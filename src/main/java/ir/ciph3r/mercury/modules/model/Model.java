package ir.ciph3r.mercury.modules.model;

import ir.ciph3r.mercury.Mercury;
import ir.ciph3r.mercury.storage.yaml.Config;
import ir.ciph3r.mercury.storage.yaml.Messages;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public abstract class Model implements Listener, CommandExecutor {
	@Getter
	private final Mercury mercury;
	@Getter
	private final String moduleName;
	@Getter
	private final String commandName;
	@Getter
	private boolean enabled;
	@Getter
	private final Config config;
	@Getter
	private final Messages messages;

	public Model(Mercury mercury, String moduleName, String commandName, boolean isEnabled) {
		this.mercury = mercury;
		this.moduleName = moduleName;
		this.commandName = commandName;
		this.enabled = isEnabled;
		this.config = mercury.getConfigFile();
		this.messages = mercury.getMessagesFile();
	}

	public void register() {
		if (!isEnabled()) return;
		getMercury().getServer().getPluginManager().registerEvents(this, getMercury());
		if (getCommandName() == null) return;
		getMercury().getCommand(getCommandName()).setExecutor(this);
	}
}