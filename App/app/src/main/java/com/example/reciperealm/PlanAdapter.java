package com.example.reciperealm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// Adaptor Class for the PlannerList RecyclerView
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    Context context;
    ArrayList<Plan> planArrayList;
    //Constructor for PlanAdapter
    public PlanAdapter(Context context, ArrayList<Plan> planArrayList) {
        this.context = context;
        this.planArrayList = planArrayList;
    }


    @NonNull
    @Override
    public PlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.plan_item,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.MyViewHolder holder, int position) {
        Plan plan = planArrayList.get(position);
        //Set Plan Item texts
        holder.recipeName.setText(plan.recipeName);
        holder.planTime.setText(String.valueOf(plan.timeChoice));
        holder.planDay.setText(plan.dayChoice);
        //Set onClickLister for planItem
        holder.planItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Redirect to RecipePage
                Intent intent = new Intent(context, RecipesPage.class);
                //Pass through Recipe Information
                intent.putExtra("recipeName", planArrayList.get(position).getRecipeName());
                intent.putExtra("recipeDesc", planArrayList.get(position).getRecipeDesc());
                intent.putExtra("recipeIngredients", planArrayList.get(position).getRecipeIngredients());
                intent.putExtra("recipeInstruct", planArrayList.get(position).getRecipeInstructions());
                //Note where the user is coming from
                intent.putExtra("PlanToken", "true");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return planArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Declare UI elements
        TextView recipeName, planTime, planDay;
        LinearLayout planItem;

        //Initialise UI elements
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.tvPlanRecipeName);
            planDay = itemView.findViewById(R.id.tvPlanDay);
            planTime = itemView.findViewById(R.id.tvPlanTime);
            planItem = itemView.findViewById(R.id.tvPlanItem);
        }
    }
}
