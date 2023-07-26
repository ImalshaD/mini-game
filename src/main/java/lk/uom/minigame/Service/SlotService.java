package lk.uom.minigame.Service;

import jakarta.transaction.Transactional;
import lk.uom.minigame.Entity.Slots;
import lk.uom.minigame.Repo.SlotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SlotService {
    private SlotRepo slotRepo;
    @Autowired
    public SlotService(SlotRepo slotRepo) {
        this.slotRepo = slotRepo;
    }

    public int findFirstSlotNumberAfterNow(){
        return slotRepo.findFirstSlotNumberAfterNow();
    }
}
