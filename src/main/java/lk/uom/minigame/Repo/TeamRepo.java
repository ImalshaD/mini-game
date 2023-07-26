package lk.uom.minigame.Repo;

import lk.uom.minigame.Entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Teams,Integer> {
    public Teams findByTeamName(String teamName);
    @Query("SELECT COUNT(t) > 0 FROM Teams t WHERE t.teamName = :teamName")
    public boolean existsByTeamName(@Param("teamName") String teamName);

}
