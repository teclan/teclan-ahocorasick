package cn.com.menglifang.pixiu.ahocorasick.emit;

import java.util.ArrayList;
import java.util.List;

public class DefaultEmitHandler implements EmitHandler {

    private List<Emit> emits = new ArrayList<Emit>();

    public void emit(Emit emit) {
        this.emits.add(emit);
    }

    public List<Emit> getEmits() {
        return this.emits;
    }

}
