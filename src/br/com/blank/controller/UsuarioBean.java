package br.com.blank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIForm;

import br.com.blank.dao.RoleDao;
import br.com.blank.dao.UsuarioDao;
import br.com.blank.model.Role;
import br.com.blank.model.Usuario;
import br.com.blank.util.FacesUtils;

@ManagedBean
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String novaSenha;
	private String confirmacaoDeSenha;
	private Role role = new Role();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private static final String ESTADO_DE_NOVO = "_novo";
	private static final String ESTADO_DE_EDICAO = "_edicao";
	private static final String ESTADO_DE_PESQUISA = "_pesquisa";

	private String state = ESTADO_DE_PESQUISA;

	private UIForm form;

	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	@ManagedProperty("#{roleDao}")
	private RoleDao roleDao;
	@ManagedProperty("#{facesUtils}")
	private FacesUtils facesUtils;

	public void lista() {
		usuarios = usuarioDao.listAll();
		setState(ESTADO_DE_PESQUISA);
	}

	public void preparaParaAdicionar() {
		this.usuario = new Usuario();
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_NOVO);
	}

	/*
	 * Todo usuário criado pelo sistema inicia com o PERFIL USUÁRIO
	 */
	public void adiciona() {

		boolean senhaInvalida = !confirmacaoDeSenha.equals(novaSenha);
		if (senhaInvalida) {
			facesUtils.adicionaMensagemDeErro("Senha de confirmação de senha não conferem.");
			return;
		}

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleDao.load("USUARIO"));
		usuario.setRoles(roles);
		usuario.setEnabled(true);
		usuario.setPassword(novaSenha);
		usuarioDao.save(usuario);
		facesUtils
				.adicionaMensagemDeInformacao("Usuário adicionado com sucesso!");
		lista();
	}

	public void remove() {
		usuario.setEnabled(false);
		usuarioDao.update(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário removido com sucesso!");
		lista();
	}

	public void preparaParaAlterar(Usuario usuario) {
		this.usuario = usuarioDao.load(usuario.getId()); // evita LazyInitializationException
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		setState(ESTADO_DE_EDICAO);
	}

	public void altera() {		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		usuario.setRoles(roles);
		usuarioDao.update(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário atualizado com sucesso!");
		lista();
	}
	
	public void alteraLoginSenha() {		
		
		boolean senhaInvalida = !confirmacaoDeSenha.equals(novaSenha);
		if (senhaInvalida) {
			facesUtils.adicionaMensagemDeErro("Senha de confirmação de senha não conferem.");
			return;
		}
		usuario.setPassword(novaSenha);
		usuarioDao.update(usuario);
		facesUtils.adicionaMensagemDeInformacao("Usuário atualizado com sucesso!");
		lista();
	}

	public void voltar() {
		this.usuario = new Usuario();
		facesUtils.cleanSubmittedValues(form); // limpa arvore
		lista();
	}

	public boolean isAdicionando() {
		return ESTADO_DE_NOVO.equals(state);
	}

	public boolean isEditando() {
		return ESTADO_DE_EDICAO.equals(state);
	}

	public boolean isPesquisando() {
		return ESTADO_DE_PESQUISA.equals(state);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}

	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}

	public void setFacesUtils(FacesUtils facesUtils) {
		this.facesUtils = facesUtils;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	

}
