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

package io.victoralbertos.breadcumbsview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.victoralbertos.breadcumbs_view.BreadcrumbsView;

public final class BreadcrumbsActivity extends AppCompatActivity {
    private Integer cacheCurrentStep;
    private BreadcrumbsView breadcrumbsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breadcrumbs);

        //Survive config changes.
        if (getLastCustomNonConfigurationInstance() == null) {
            cacheCurrentStep = 0;
        } else {
            cacheCurrentStep = (Integer) getLastCustomNonConfigurationInstance();
        }

        breadcrumbsView = (BreadcrumbsView) findViewById(R.id.breadcrumbs);
        breadcrumbsView.setCurrentStep(cacheCurrentStep);

        findViewById(R.id.bt_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breadcrumbsView.nextStep();
            }
        });

        findViewById(R.id.bt_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breadcrumbsView.prevStep();
            }
        });

        breadcrumbsView.setOnClickedListener(new BreadcrumbsView.OnClickListener() {
            @Override
            public void onClicked(int index, boolean visited, View view) {
                if (visited)
                    Toast.makeText(BreadcrumbsActivity.this, "Clicked " + index + "th item, wich is visited ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BreadcrumbsActivity.this, "Clicked " + index + "th item, wich isn't visited   ", Toast.LENGTH_SHORT).show();
            }
        });

        breadcrumbsView.setOnLongClickListener(new BreadcrumbsView.OnLongClickListener() {
            @Override
            public boolean onLongClicked(int index, boolean visited, View view) {
                if (visited)
                    Toast.makeText(BreadcrumbsActivity.this, "Long Clicked " + index + "th item, wich is visited ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(BreadcrumbsActivity.this, "Logn Clicked " + index + "th item, wich isn't visited   ", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //Survive config changes.
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return breadcrumbsView.getCurrentStep();
    }
}
