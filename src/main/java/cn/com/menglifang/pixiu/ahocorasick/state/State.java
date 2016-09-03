package cn.com.menglifang.pixiu.ahocorasick.state;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class State {

    /** effective the size of the keyword */
    private final int depth;

    /**
     * only used for the root state to refer to itself in case no matches have
     * been found
     */
    private final State rootState;

    /**
     * referred to in the white paper as the 'goto' structure. From a state it
     * is possible to go
     * to other states, depending on the character passed.
     */
    private Map<Character, State> success = new HashMap<Character, State>();

    /** if no matching states are found, the failure state will be returned */
    private State failure = null;

    /**
     * whenever this state is reached, it will emit the matches keywords for
     * future reference
     */
    private Set<String> emits = null;

    public State() {
        this(0);
    }

    public State(int depth) {
        this.depth = depth;
        this.rootState = depth == 0 ? this : null;
    }

    private State nextState(Character character, boolean ignoreRootState) {
        State nextState = this.success.get(character);
        if (!ignoreRootState && nextState == null && this.rootState != null) {
            nextState = this.rootState;
        }
        return nextState;
    }

    public State nextState(Character character) {
        return nextState(character, false);
    }

    public State nextStateIgnoreRootState(Character character) {
        return nextState(character, true);
    }

    public State addState(Character character) {
        State nextState = nextStateIgnoreRootState(character);
        if (nextState == null) {
            nextState = new State(this.depth + 1);
            this.success.put(character, nextState);
        }
        return nextState;
    }

    public int getDepth() {
        return this.depth;
    }

    public void addEmit(String keyword) {
        if (this.emits == null) {
            this.emits = new TreeSet<String>();
        }
        this.emits.add(keyword);
    }

    public void addEmit(Collection<String> emits) {
        for (String emit : emits) {
            addEmit(emit);
        }
    }

    public Collection<String> emit() {
        return this.emits == null ? Collections.<String> emptyList()
                : this.emits;
    }

    public State failure() {
        return this.failure;
    }

    public void setFailure(State failState) {
        this.failure = failState;
    }

    public Collection<State> getStates() {
        return this.success.values();
    }

    public Collection<Character> getTransitions() {
        return this.success.keySet();
    }
}
