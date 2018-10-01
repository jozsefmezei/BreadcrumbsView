/*
 * Copyright 2016 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.victoralbertos.breadcumbs_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

@SuppressLint("ViewConstructor")
final class DotView extends RelativeLayout {
    private final View dotViewVisitedStep;
    private View dotView;

    DotView(Context context, boolean visited, int visitedStepBorderDotColor, int visitedStepFillDotColor, int nextStepBorderDotColor, int nextStepFillDotColor, int radius, int sizeBorderLine, int index, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, View.OnTouchListener onTouchListener) {
        super(context);

        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        addView(createDot(nextStepBorderDotColor, nextStepFillDotColor, radius, sizeBorderLine, index, onClickListener, onLongClickListener, onTouchListener));

        dotViewVisitedStep = createDot(visitedStepBorderDotColor, visitedStepFillDotColor, radius, sizeBorderLine, index, onClickListener, onLongClickListener, onTouchListener);

        if (!visited) {
            dotViewVisitedStep.setScaleX(0);
            dotViewVisitedStep.setScaleY(0);
        }

        addView(dotViewVisitedStep);

        //For testing purpose
        setTag("dotView");
        dotViewVisitedStep.setTag("dotViewVisitedStep");
        dotViewVisitedStep.setTag(R.integer.dot_test_key, "dotViewVisitedStep");
    }

    private View createDot(int borderColor, int fillColor, int radius, int sizeBorderLine, int index, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, View.OnTouchListener onTouchListener) {
        dotView = new View(getContext());
        dotView.setLayoutParams(new LinearLayout.LayoutParams(radius * 2, radius * 2));

        GradientDrawable border = new GradientDrawable();
        border.setShape(GradientDrawable.OVAL);
        border.setColor(fillColor);
        border.setStroke(sizeBorderLine, borderColor);

        dotView.setBackground(border);
        dotView.setTag(R.integer.dot_index_key, index);

        if (onClickListener != null) dotView.setOnClickListener(onClickListener);
        if (onLongClickListener != null) dotView.setOnLongClickListener(onLongClickListener);
        if (onTouchListener != null) dotView.setOnTouchListener(onTouchListener);

        return dotView;
    }

    void animateToVisitedStep(Runnable endAnim) {
        dotViewVisitedStep
                .animate()
                .scaleX(1)
                .scaleY(1)
                .withEndAction(endAnim);
        dotView.setTag(R.integer.dot_index_visited, true);
    }

    void animateFromVisitedStepToBeforeStep(Runnable endAnim) {
        dotView.setTag(R.integer.dot_index_visited, false);
        dotViewVisitedStep
                .animate()
                .scaleX(0)
                .scaleY(0)
                .withEndAction(endAnim);
    }
}

