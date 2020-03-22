package br.ufpr.tcc.gregs.models;

public class Retorno {

	private String msg;
	private Object data;

	public Retorno() {
	}

	public Retorno(String msg, Object data) {
		this.msg = msg;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
