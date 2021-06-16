package com.ftp.cn.ftp.bo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "sftp.client")
public class SftpProperties {
    private String host;
    private Integer port;
    private String protocol;
    private String username;
    private String password;
    private String root;
    private String privateKey;
    private String passphrase;
    private String sessionStrictHostKeyChecking;
    private Integer sessionConnectTimeout;
    private Integer channelConnectedTimeout;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPassphrase() {
		return passphrase;
	}
	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
	public String getSessionStrictHostKeyChecking() {
		return sessionStrictHostKeyChecking;
	}
	public void setSessionStrictHostKeyChecking(String sessionStrictHostKeyChecking) {
		this.sessionStrictHostKeyChecking = sessionStrictHostKeyChecking;
	}
	public Integer getSessionConnectTimeout() {
		return sessionConnectTimeout;
	}
	public void setSessionConnectTimeout(Integer sessionConnectTimeout) {
		this.sessionConnectTimeout = sessionConnectTimeout;
	}
	public Integer getChannelConnectedTimeout() {
		return channelConnectedTimeout;
	}
	public void setChannelConnectedTimeout(Integer channelConnectedTimeout) {
		this.channelConnectedTimeout = channelConnectedTimeout;
	}
    
}
