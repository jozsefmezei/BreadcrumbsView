package io.victoralbertos.breadcumbs_view;

import android.view.View;

public class TagParser {

    static TagModel parseTag(View view) {
        int index = -1;
        boolean visited = false;

        if (view.getTag(R.integer.dot_index_key) instanceof Integer)
            index = (Integer) (view.getTag(R.integer.dot_index_key));
        if (index == 0) visited = true;
        else if (view.getTag(R.integer.dot_index_visited) instanceof Boolean)
            visited = (Boolean) (view.getTag(R.integer.dot_index_visited));
        return new TagModel(index, visited);
    }

    static class TagModel {
        int index;
        boolean visited;

        public TagModel(int index, boolean visited) {
            this.index = index;
            this.visited = visited;
        }

        public int getIndex() {
            return index;
        }

        public boolean isVisited() {
            return visited;
        }
    }
}