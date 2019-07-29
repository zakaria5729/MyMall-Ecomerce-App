package com.zakariahossain.ecomerceapp.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryBinding categoryBinding;

        CategoryViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.categoryBinding = binding;

            categoryBinding.getRoot().setOnClickListener(view -> {
                if (getAdapterPosition() != 0 && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(categoryBinding.getRoot().getContext(), CategoryActivity.class);
                    intent.putExtra("category_name", categoryList.get(getAdapterPosition()).getCategoryName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    categoryBinding.getRoot().getContext().startActivity(intent);
                }
            });
        }

        void bind(Category category) {
            categoryBinding.setACategory(category);
            categoryBinding.executePendingBindings();
        }
    }
}
