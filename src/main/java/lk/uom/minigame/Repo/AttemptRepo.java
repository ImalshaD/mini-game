package lk.uom.minigame.Repo;

import lk.uom.minigame.Entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt,Integer> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Attempt a WHERE a.team.teamName = :teamName")
    public boolean existsByTeamName(@Param("teamName") String teamName);
    @Query("SELECT a FROM Attempt a " +
            "WHERE a.team.teamName = :teamName " +
            "ORDER BY a.startTime DESC")
    @Nullable
    public Attempt findLatestAttemptByTeamName(@Param("teamName") String teamName);
}
