<template>
  <div>
    <v-form v-model="valid" @submit="submit" ref="form">
      <v-container>
        <v-row>
          <v-col cols="4" offset="4">
            <v-text-field
              ref="field"
              v-model="price"
              label="Preis"
              required
              placeholder="0,00€"
              @change="formatPrice"
              @focus="unformatPrice"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col offset="4">
            <v-btn @click="$refs.form.$emit('submit')">Absenden</v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <v-chip
      ref="chip"
      class="chip"
      style="position: fixed; z-index: 10000"
      v-show="chipPrice"
    >
      {{ chipPrice }}
    </v-chip>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";

@Component({
  name: "Home",
})
export default class Home extends Vue {
  $refs!: {
    chip: any;
    field: any;
    form: any;
  };

  price: string | null | number = null;
  valid = true;
  chipPrice: string | null = null;
  flyDistanceXTotal = 0;
  flyDistanceYTotal = 0;
  flyDistanceX = 0;
  flyDistanceY = 0;
  flyInterval: number | undefined;

  formatPrice(): void {
    this.price = new Intl.NumberFormat("de-DE", {
      style: "currency",
      currency: "EUR",
    }).format(this.removeJunk(this.price));
  }
  unformatPrice(): void {
    if (this.price) {
      this.price = this.removeJunk(this.price);
    }
  }
  removeJunk(input: string | number | null): number {
    let output = "" + input;
    output = output.replaceAll(".", "");
    output = output.replace(",", ".");
    output = output.replace("€", "");
    output = output.replace(" ", "");
    return +output;
  }
  submit(event: Event): void {
    if (event) {
      event.preventDefault();
    }
    this.chipPrice = String(this.price);
    this.$refs.chip.$el.style.top =
      this.$refs.field.$el.getBoundingClientRect().y + "px";
    this.$refs.chip.$el.style.left =
      this.$refs.field.$el.getBoundingClientRect().x + "px";
    this.flyDistanceXTotal = this.$refs.field.$el.getBoundingClientRect().x;
    this.flyDistanceX = this.flyDistanceXTotal / 100;
    this.flyDistanceYTotal = this.$refs.field.$el.getBoundingClientRect().y;
    this.flyDistanceY = this.flyDistanceYTotal / 100;
    this.flyInterval = window.setInterval(() => {
      this.flyDistanceXTotal = this.flyDistanceXTotal - this.flyDistanceX;
      this.$refs.chip.$el.style.left = this.flyDistanceXTotal + "px";

      this.flyDistanceYTotal = this.flyDistanceYTotal - this.flyDistanceY;
      this.$refs.chip.$el.style.top = this.flyDistanceYTotal + "px";
      if (this.flyDistanceXTotal <= 15) {
        this.chipPrice = null;
        window.clearInterval(this.flyInterval);
      }
    }, 10);
    this.price = null;
  }
}
</script>

<style>
@keyframes flyToHistory {
  0% {
    transform: scale(1);
  }
  10% {
    transform: scale(0.99);
  }
  20% {
    transform: scale(0.95);
  }
  100% {
    transform: scale(0);
  }
}

.chip {
  animation: 1s flyToHistory ease-in;
}
</style>
