package org.sellerofideas.makingof.repositorio;

import org.sellerofideas.makingof.model.Perfil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PerfilRepo extends CrudRepository<Perfil, Integer>{
    @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from perfis where id = :id", nativeQuery = true)
  public boolean exist(int id);

  @Query(value="select * from perfis where email = :email and senha = :senha", nativeQuery = true)
  public Perfil Login(String email, String senha);
}
