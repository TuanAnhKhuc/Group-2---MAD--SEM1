package com.example.reciperealm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Recipes> recipesArrayList;

    public RecipeAdapter(Context context, ArrayList<Recipes> recipesArrayList) {
        this.context = context;
        this.recipesArrayList = recipesArrayList;
    }


    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recipe_item,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {
        Recipes recipe = recipesArrayList.get(position);

        holder.recipeName.setText(recipe.Name);
        holder.serves.setText(String.valueOf(recipe.Serves));
        holder.prepTime.setText(recipe.Prep);
        holder.cookTime.setText(recipe.Cook);

        holder.recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecipesPage.class);
                intent.putExtra("recipeName", recipesArrayList.get(position).getName());
                intent.putExtra("recipeDesc", recipesArrayList.get(position).getDescription());
                intent.putExtra("recipeIngredients", recipesArrayList.get(position).getIngredients());
                intent.putExtra("recipeInstruct", recipesArrayList.get(position).getInstructions());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName, serves, prepTime, cookTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.tvRecipeName);
            serves = itemView.findViewById(R.id.tvServes);
            prepTime = itemView.findViewById(R.id.tvPrepTime);
            cookTime = itemView.findViewById(R.id.tvCookTime);
        }
    }
}
