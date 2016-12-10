package io.vevox.sandcannon;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * SandCannon main class
 * @author Matthew Struble
 */
@SuppressWarnings("WeakerAccess")
public class SandCannon extends JavaPlugin {

  private static SandCannon instance;

  @Override
  public void onLoad() {
    instance = this;
  }

  @Override
  public void onEnable() {

  }

  @Override
  public void onDisable() {

  }

  public static SandCannon getInstance() {
    return instance;
  }

}
