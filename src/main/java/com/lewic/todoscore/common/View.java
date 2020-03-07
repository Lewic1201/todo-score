package com.lewic.todoscore.common;

/**
 * @author lewic
 * @since 2020/1/4 21:16
 * <p>
 * JsonView的视图关系
 * </p>
 */
public class View {

    public interface Summary {
    }

    public interface Detail extends Summary {
    }

    public interface NoShow {
    }
}
