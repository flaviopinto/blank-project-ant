package br.com.blank.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blank.dao.RoleDao;
import br.com.blank.dao.UsuarioDao;
import br.com.blank.model.Role;
import br.com.blank.model.Usuario;

@Service("bootstrap")
public class Bootstrap {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RoleDao perfilDao;

	public void setup() {

		carregaUsuario();

	}

	private void carregaUsuario() {	

		Role user = perfilDao.load("USUARIO");
		if (user == null) {
			LogUtil.logInfo("Criando perfil USU�RIO");
			user = new Role();
			user.setName("USUARIO");
			perfilDao.save(user);
			LogUtil.logInfo("Perfil USU�RIO criado");
		}

		Role especial = perfilDao.load("ESPECIAL");
		if (especial == null) {
			LogUtil.logInfo("Criando perfil ESPECIAL");
			especial = new Role();
			especial.setName("ESPECIAL");
			perfilDao.save(especial);
			LogUtil.logInfo("Perfil ESPECIAL criado");
		}

		Role admin = perfilDao.load("ADMINISTRADOR");
		if (admin == null) {
			LogUtil.logInfo("Criando perfil AMINISTRADOR");
			admin = new Role();
			admin.setName("ADMINISTRADOR");
			perfilDao.save(admin);
			LogUtil.logInfo("Perfil AMINISTRADOR criado");
		}

		List<Role> roles = new ArrayList<Role>();
		roles.add(admin);

		LogUtil.logInfo("Criando usu�rio SUPER USER");
		Usuario usuario = new Usuario();
		usuario.setNome("USU�RIO SUPER USER");
		usuario.setUsername("root");
		usuario.setPassword("sysadmin");
		usuario.setEnabled(true);
		usuario.setMainUser(true);
		usuario.setRoles(roles);

		usuarioDao.save(usuario);
		LogUtil.logInfo("Usu�rio SUPER ADMIN criado");
		
		LogUtil.logInfo("Criando usu�rio ADMIN");
		usuario = new Usuario();
		usuario.setNome("USU�RIO ADMIN");
		usuario.setUsername("admin");
		usuario.setPassword("admin");
		usuario.setEnabled(true);
		usuario.setRoles(roles);

		usuarioDao.save(usuario);
		LogUtil.logInfo("Usu�rio ADMIN criado");
	}

}
