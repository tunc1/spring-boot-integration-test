package app.controller;

import app.entity.Item;
import app.service.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/item")
public class ItemController
{
	private ItemService itemService;
	public ItemController(ItemService itemService)
	{
		this.itemService=itemService;
	}
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Item save(@RequestBody Item item)
	{
		return itemService.save(item);
	}
	@PutMapping("/{id}")
	public Item update(@RequestBody Item item,@PathVariable Long id)
	{
		item.setId(id);
		return itemService.update(item);
	}
	@GetMapping("/{id}")
	public Item findById(@PathVariable Long id)
	{
		return itemService.findById(id);
	}
	@GetMapping
	public Page<Item> findAll(Pageable pageable)
	{
		return itemService.findAll(pageable);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id)
	{
		itemService.deleteById(id);
	}
}