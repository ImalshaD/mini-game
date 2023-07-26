package lk.uom.minigame.Repo;

import lk.uom.minigame.Entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepo extends JpaRepository<Slots,Integer> {
    @Query("SELECT s.id FROM Slots s WHERE s.start_time > CURRENT_TIME ORDER BY s.start_time ASC")
    Integer findFirstSlotNumberAfterNow();
}
