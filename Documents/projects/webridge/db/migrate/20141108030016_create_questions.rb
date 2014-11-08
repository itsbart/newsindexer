class CreateQuestions < ActiveRecord::Migration
  def change
    create_table :questions do |t|
      t.string :name
      t.string :class_name
      t.text :content

      t.timestamps
    end
  end
end
