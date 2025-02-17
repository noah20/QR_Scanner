package com.dev.qrscanner.main.ui.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.qrscanner.R;
import com.dev.qrscanner.databinding.CodeItemLayoutBinding;
import com.dev.qrscanner.main.data.model.QrCodeModel;

import java.util.ArrayList;
import java.util.List;

public class QrCodesAdapter extends RecyclerView.Adapter<QrCodesAdapter.QrCodeViewHolder> {

    public interface OnFavoriteClick {
        void onClick(QrCodeModel qrCodeModel);
    }

    protected OnFavoriteClick mListener;

    public void setListener(OnFavoriteClick listener) {
        mListener = listener;
    }

    private final ArrayList<QrCodeModel> codeModels = new ArrayList<>();

    public void setCodes(List<QrCodeModel> codeModels) {
        if (codeModels == null || codeModels.isEmpty())
            return;
        this.codeModels.clear();
        this.codeModels.addAll(codeModels);
        notifyItemRangeChanged(0, codeModels.size());
    }

    @NonNull
    @Override
    public QrCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CodeItemLayoutBinding binding = CodeItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new QrCodeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QrCodeViewHolder holder, int position) {
        QrCodeModel obj = codeModels.get(position);
        holder.bindData(obj);
        holder.binding.ivFavorite.setOnClickListener(view -> {
            if(mListener != null){
                mListener.onClick(obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return codeModels.size();
    }

    public static class QrCodeViewHolder extends RecyclerView.ViewHolder {
        CodeItemLayoutBinding binding;

        public QrCodeViewHolder(@NonNull CodeItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindData(QrCodeModel qrCodeModel) {
            if (binding != null) {
                binding.tvCode.setText(qrCodeModel.getQrCode());
                binding.ivFavorite.setImageResource(qrCodeModel.isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_un_favorite);
            }

        }
    }
}
